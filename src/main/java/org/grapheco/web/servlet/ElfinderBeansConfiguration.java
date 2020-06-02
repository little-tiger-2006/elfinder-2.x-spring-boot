package org.grapheco.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.grapheco.elfinder.controller.ConnectorController;
import org.grapheco.elfinder.controller.executor.CommandExecutorFactory;
import org.grapheco.elfinder.controller.executor.DefaultCommandExecutorFactory;
import org.grapheco.elfinder.controller.executors.MissingCommandExecutor;
import org.grapheco.elfinder.controller.executors.OpenCommandExecutor;
import org.grapheco.elfinder.impl.DefaultFsService;
import org.grapheco.elfinder.impl.DefaultFsServiceConfig;
import org.grapheco.elfinder.impl.FsSecurityCheckForAll;
import org.grapheco.elfinder.impl.StaticFsServiceFactory;
import org.grapheco.elfinder.localfs.LocalFsVolume;
import org.grapheco.elfinder.service.FsItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.util.StringUtils;

/**
 * Configuration is a substitute for beans and bean in the
 * 'elfinder-servlet.xml'
 * 
 * users should extend from this class and customize required protected methods.
 * 
 * @author little-tiger
 *
 */
@Configuration
public class ElfinderBeansConfiguration {

	@Autowired
	VolumesProperties volumes;

	/**
	 * create a command executor factory
	 * 
	 * @param config
	 * @return
	 */
	@Bean(name = "commandExecutorFactory")
	protected CommandExecutorFactory createCommandExecutorFactory() {
		DefaultCommandExecutorFactory defaultCommandExecutorFactory = new DefaultCommandExecutorFactory();
		defaultCommandExecutorFactory
				.setClassNamePattern(OpenCommandExecutor.class.getPackage().getName()
						+ ".%sCommandExecutor");
		defaultCommandExecutorFactory.setFallbackCommand(new MissingCommandExecutor());
		return defaultCommandExecutorFactory;
	}

	/**
	 * create a connector controller for servlet
	 * 
	 * @param config
	 * @return
	 */
	@Bean
	protected ConnectorController createConnectorController() {
		ConnectorController connectorController = new ConnectorController();
		connectorController.setCommandExecutorFactory(createCommandExecutorFactory());
		connectorController.setFsServiceFactory(createServiceFactory());

		return connectorController;
	}

	public static String userfilesBaseDir() {
		String baseDir = FileUtils.getUserDirectoryPath();
		return baseDir + File.separator + "FileUploadDirectory";
	}

	/**
	 * Default fs service
	 * 
	 * @return
	 */
	@Bean(name = "fsService")
	protected DefaultFsService createFsService() {
		DefaultFsService fsService = new DefaultFsService();
		fsService.setSecurityChecker(new FsSecurityCheckForAll());

		DefaultFsServiceConfig serviceConfig = new DefaultFsServiceConfig();
		serviceConfig.setTmbWidth(80);
		fsService.setServiceConfig(serviceConfig);

		List<Map<String, String>> configVolumes = volumes.getVolumes();

		String userfilesBaseDir = userfilesBaseDir();

		if (configVolumes != null && configVolumes.size() > 0) {
			for (Map<String, String> volume : configVolumes) {
				String id = volume.get("id");
				String title = volume.get("title");
				String path = volume.get("path");
				if (!path.startsWith(userfilesBaseDir)) {
					path = userfilesBaseDir + File.separator + path;
				}
				LocalFsVolume lvolume = createLocalFsVolume(title, new File(path));
				fsService.addVolume(id, lvolume);

				String subdirectory = volume.get("subdirectory");
				if (!StringUtils.isEmpty(subdirectory)) {
					String[] folders = subdirectory.split(";");

					for (String folder : folders) {

						if (!StringUtils.isEmpty(folder)) {
							FsItem item = lvolume.fromPath(folder);
							try {
								lvolume.createFolder(item);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}

		} else

		{
			// default demo volume
			fsService.addVolume("A",
					createLocalFsVolume("My Files", new File(userfilesBaseDir + File.pathSeparator + "/tmp/a")));
			fsService.addVolume("B",
					createLocalFsVolume("Shared", new File(userfilesBaseDir + File.pathSeparator + "/tmp/b")));
		}
		return fsService;
	}

	private LocalFsVolume createLocalFsVolume(String name, File rootDir) {
		LocalFsVolume localFsVolume = new LocalFsVolume();
		localFsVolume.setName(name);
		localFsVolume.setRootDir(rootDir);
		return localFsVolume;
	}

	/**
	 * create a service factory
	 * 
	 * @param config
	 * @return
	 */
	@Bean(name = "fsServiceFactory")
	protected StaticFsServiceFactory createServiceFactory() {
		StaticFsServiceFactory staticFsServiceFactory = new StaticFsServiceFactory();
		DefaultFsService fsService = createFsService();

		staticFsServiceFactory.setFsService(fsService);
		return staticFsServiceFactory;
	}
}

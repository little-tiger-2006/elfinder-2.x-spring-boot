package org.grapheco.web.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 
 * @author little-tiger
 *
 */
@Configuration
@ConfigurationProperties(prefix = "com.little.tiger")
//如果只有一个主配置类文件，@PropertySource可以不写
@PropertySource(value="classpath:elfinder-volumes.yml", encoding = "UTF-8", factory = YamlPropertyLoaderFactory.class)
public class VolumesProperties {
    private List<Map<String,String>> volumes = new ArrayList<>();
    private String userFilePath;
    
	public List<Map<String,String>> getVolumes() {
		return volumes;
	}

	public void setVolumes(List<Map<String,String>> volumes) {
		this.volumes = volumes;
	}

	public String getUserFilePath() {
		return userFilePath;
	}

	public void setUserFilePath(String userFilePath) {
		this.userFilePath = userFilePath;
	}
}

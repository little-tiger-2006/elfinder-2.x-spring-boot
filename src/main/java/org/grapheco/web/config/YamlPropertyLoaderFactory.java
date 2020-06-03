package org.grapheco.web.config;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;
import java.io.IOException;

/**
 * the DefaultPropertySourceFactory can't read complicated configuration in the
 * yml,here is the alternative plan, from
 * https://stackoverflow.com/questions/21271468/spring-propertysource-using-yaml/51392715
 * 
 * @author little-tiger
 * 
 */
public class YamlPropertyLoaderFactory extends DefaultPropertySourceFactory {
	@Override
	public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
		if (resource == null) {
			return super.createPropertySource(name, resource);
		}

		return new YamlPropertySourceLoader().load(resource.getResource().getFilename(), resource.getResource(), null);
	}
}

package com.ahom.appInfo;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.ahom.commonUtil.logging.GrepFriendlyLogger;

public class AerMedsOrdersAppInfo extends DependencyInfo implements InitializingBean
{
	private static final Logger log = new GrepFriendlyLogger(AerMedsOrdersAppInfo.class);
	private static final String propFile = "/AerMedsOrdersAppInfo.prop";

	public AerMedsOrdersAppInfo() {
		Properties props;
		try {
			props = PropertiesLoaderUtils.loadProperties(new ClassPathResource(propFile));
		} catch(IOException ioe) {
			throw new RuntimeException("Could not load "+propFile, ioe);
		}
		
		setName(props.getProperty("application.name"));
		setVersion(props.getProperty("application.version"));
		setArtifact(props.getProperty("application.artifact"));
		setGroup(props.getProperty("application.group"));
		setPomDependencyInfo(props.getProperty("application.dependencies"));
		
		addDependency(new CommonUtilInfo());
		addDependency(new CommonUIInfo());
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		AppInfo.setAppInfo(new AerMedsOrdersAppInfo());
		log.info("Deployed AppInfo build:\n" + AppInfo.toDependencyTree());
	}
}

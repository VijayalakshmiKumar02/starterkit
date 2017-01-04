package com.cognizant.deployprocessorSupport.scheduler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cognizant.deployprocessorSupport.DeployProcessorSupportToolApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DeployProcessorSupportToolApplication.class)
public class GistCleanupSchedulerTest {

@Autowired
	private GistCleanupScheduler gistCleanupScheduler;

	@Test
	public void directoryCleanupSchedulerTest() {
		//gistCleanupScheduler.cronHandlerGIST();
	}
	

}

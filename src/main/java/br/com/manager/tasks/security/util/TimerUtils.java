package br.com.manager.tasks.security.util;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;

public final class TimerUtils {
	private TimerUtils() {
	}

	public static JobDetail buildJobDetail(final Class jobClass) {
		final JobDataMap jobDataMap = new JobDataMap();

		return JobBuilder.newJob(jobClass).withIdentity(jobClass.getSimpleName()).setJobData(jobDataMap).build();
	}
}

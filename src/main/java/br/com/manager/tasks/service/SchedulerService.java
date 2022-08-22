package br.com.manager.tasks.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {
	private static final Logger LOG = LoggerFactory.getLogger(SchedulerService.class);
	private final Scheduler scheduler;

	@Autowired
	public SchedulerService(final Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public <T extends Job> void schedule(final Class<T> jobClass, final Trigger trigger) {
		final JobDataMap jobDataMap = new JobDataMap();

		JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobClass.getSimpleName()).setJobData(jobDataMap)
				.build();

		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	@PostConstruct
	public void init() {
		try {
			scheduler.start();
		} catch (SchedulerException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	@PreDestroy
	public void preDestroy() {
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			LOG.error(e.getMessage(), e);
		}
	}
}

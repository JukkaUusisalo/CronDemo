package org.uusisalo.crondemo

import org.quartz._
import org.quartz.impl.StdSchedulerFactory


object Main {

  def main(args: Array[String]):Unit = {
    println("This is cron demo!")
    initCron()
    System.in.read
  }

  def initCron() = {

    lazy val cron = StdSchedulerFactory.getDefaultScheduler

    val crontask = JobBuilder.newJob(classOf[CronJob]).withIdentity("jobName").build
    val trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")).build
    cron.start()
    cron.scheduleJob(crontask,trigger)

  }
}

class CronJob extends Job {
  override def execute(context: JobExecutionContext) = {
    println("Something happens " + context.getJobDetail.getKey.getName)
  }
}

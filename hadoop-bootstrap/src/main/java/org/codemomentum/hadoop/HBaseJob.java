package org.codemomentum.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.codemomentum.hadoop.hbase.AuthLog2HbaseMapper;

/**
 * @author codemomentum@gmail.com
 */
public class HBaseJob {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		conf.set(TableOutputFormat.OUTPUT_TABLE, "articles");
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		String input = otherArgs[0];
		Job job = new Job(conf, "Populate Articles Table with " + input);
		// Input is just text files in HDFS
		FileInputFormat.addInputPath(job, new Path(input));
		job.setJarByClass(HBaseJob.class);
		job.setMapperClass(AuthLog2HbaseMapper.class);
		job.setNumReduceTasks(0);
		// Output is to the table output format, and we set the table we want
		job.setOutputFormatClass(TableOutputFormat.class);
		job.waitForCompletion(true);
	}
}

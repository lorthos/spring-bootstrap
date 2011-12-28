package org.codemomentum.hadoop.auth;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

/**
 * @author codemomentum@gmail.com
 */
public class AuthLogMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
	private final static IntWritable one = new IntWritable(1);

	@Override
	public void map(LongWritable key, Text value,
					OutputCollector<Text, IntWritable> output,
					Reporter reporter) throws IOException {
		String line = value.toString();
		try {
			AuthLogRecord record = AuthlogLineParser.parseLine(line);
			if (null != record) {
				if (record.getMessage().contains("Invalid user")) {
					String[] splitted = record.getMessage().split(" ");
					output.collect(new Text(splitted[4]), one);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error in map!");
		}
	}
}

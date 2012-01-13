package org.codemomentum.hadoop.hbase;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Mapper;
import org.codemomentum.hadoop.auth.AuthLogRecord;
import org.codemomentum.hadoop.auth.AuthlogLineParser;

import java.io.IOException;

/**
 * @author codemomentum@gmail.com
 */
public class AuthLog2HbaseMapper extends Mapper<LongWritable, Text, NullWritable, Writable> {

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();
		try {
			AuthLogRecord record = AuthlogLineParser.parseLine(line);
			Put put = new Put(key.toString().getBytes());

			if (null != record) {
				put.add(Bytes.toBytes("content"), Bytes.toBytes("date"), Bytes.toBytes(record.getDate().toString()));

				put.add(Bytes.toBytes("content"), Bytes.toBytes("daemon"), Bytes.toBytes(record.getDaemon()));
				put.add(Bytes.toBytes("content"), Bytes.toBytes("process"), Bytes.toBytes(record.getProcess()));
				put.add(Bytes.toBytes("content"), Bytes.toBytes("pid"), Bytes.toBytes(record.getPid()));
				put.add(Bytes.toBytes("content"), Bytes.toBytes("message"), Bytes.toBytes(record.getMessage()));

				context.write(NullWritable.get(), put);
			}


		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error in map!", e);
		}
	}
}

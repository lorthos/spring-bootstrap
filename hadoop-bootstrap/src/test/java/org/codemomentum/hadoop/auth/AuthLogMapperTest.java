package org.codemomentum.hadoop.auth;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mrunit.MapReduceDriver;
import org.junit.Before;
import org.junit.Test;

/**
 * @author codemomentum@gmail.com
 */
public class AuthLogMapperTest {
	private Mapper mapper;
	private MapReduceDriver driver;
	private Reducer reducer;

	@Before
	public void setUp() {
		mapper = new AuthLogMapper();
		reducer = new AuthLogReducer();
		driver = new MapReduceDriver(mapper, reducer);
	}

	@Test
	public void testIdentityMapper() {
		driver.withInput(new LongWritable(1l), new Text("Dec 18 11:08:33 ip-11-111-111-111 sshd[6893]: Invalid user handan from 118.219.234.227"))
				.withInput(new LongWritable(2l), new Text("Dec 18 11:08:48 ip-11-111-111-111 sshd[6909]: Invalid user field from 118.219.234.227"))
				.withInput(new LongWritable(3l), new Text("Dec 19 21:41:24 ip-11-111-111-111 sshd[21212]: Invalid user admin from 123.49.55.132"))
				.withInput(new LongWritable(4l), new Text("Dec 19 21:43:21 ip-11-111-111-111 sshd[21300]: Invalid user admin from 123.49.55.132"))
				.withOutput(new Text("118.219.234.227"), new IntWritable(2))
				.withOutput(new Text("123.49.55.132"), new IntWritable(2))
				.runTest();
	}
}

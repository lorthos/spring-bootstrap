package org.codemomentum.hadoop.hbase;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;

/**
 * @author codemomentum@gmail.com
 */
public class AuthLog2HbaseMapperTest {

	private MapDriver driver;

	@Before
	public void setUp() {
		AuthLog2HbaseMapper mapper = new AuthLog2HbaseMapper();
		driver = new MapDriver(mapper);
	}

	@Test
	public void testBasicFlow() {
		driver.withInput(new LongWritable(1l), new Text("Dec 18 11:08:33 ip-11-111-111-111 sshd[6893]: Invalid user handan from 118.219.234.227"))
				.withInput(new LongWritable(2l), new Text("Dec 18 11:08:48 ip-11-111-111-111 sshd[6909]: Invalid user field from 118.219.234.227"))
				.withInput(new LongWritable(3l), new Text("Dec 19 21:41:24 ip-11-111-111-111 sshd[21212]: Invalid user admin from 123.49.55.132"))
				.withInput(new LongWritable(4l), new Text("Dec 19 21:43:21 ip-11-111-111-111 sshd[21300]: Invalid user admin from 123.49.55.132"))
				.withInput(new LongWritable(4l), new Text("Dec 19 21:43:21 ip-11-111-111-111 sshd[21302]: Invalid user mysql from 123.49.55.133"))
				.runTest();
	}
}

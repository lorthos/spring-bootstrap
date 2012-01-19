package org.codemomentum.hadoop.hbase;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author codemomentum@gmail.com
 */
public class AuthLog2HbaseMapperTest {
	AuthLog2HbaseMapper mapper;
	Mapper.Context mockContext;


	@Before
	public void setUp() {
		mapper = new AuthLog2HbaseMapper();
		mockContext = mock(Mapper.Context.class);
	}

	@Test
	public void testBasicFlow() throws Exception {
		mapper.map(new LongWritable(1l), new Text("Dec 18 11:08:33 ip-11-111-111-111 sshd[6893]: Invalid user oracle from 118.219.234.227"), mockContext);

		ArgumentCaptor<Put> argument = ArgumentCaptor.forClass(Put.class);
		verify(mockContext).write(anyObject(), argument.capture());
		assertTrue("Should contain the message as content:message",
				argument.getValue().has(Bytes.toBytes("content"), Bytes.toBytes("message"), Bytes.toBytes("Invalid user oracle from 118.219.234.227")));


		verifyNoMoreInteractions(mockContext);

	}
}

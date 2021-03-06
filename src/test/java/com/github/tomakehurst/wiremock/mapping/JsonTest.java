/*
 * Copyright (C) 2011 Thomas Akehurst
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.tomakehurst.wiremock.mapping;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class JsonTest {

	private static final String TEST_VALUE = "test-value";
	private static final String JSON_WITH_NO_COMMENTS = 
			"{                                          \n" +
                "\"property\": \"" + TEST_VALUE + "\"   \n" +
            "}";
	
	private static final String JSON_WITH_COMMENTS =
			"// this is the first comment                                                   \n" +
            "{                                                                              \n" +
                    "//this is a comment                                                    \n" +
                    "\"property\": \"" + TEST_VALUE + "\"// comment on same line as code    \n" +
            "}                                                                              \n" +
             "//this is the last comment";
	
	@Test
	public void testReadNoComments() {
		TestPojo pojo = Json.read(JSON_WITH_NO_COMMENTS, TestPojo.class);
		assertNotNull(pojo);
		assertThat(TEST_VALUE, is(pojo.property));
	}
	
	@Test
	public void testReadWithComments() {
		TestPojo pojo = Json.read(JSON_WITH_COMMENTS, TestPojo.class);
		assertNotNull(pojo);
		assertThat(TEST_VALUE, is(pojo.property));
	}
	
	private static class TestPojo {
		public String property;
	}
}

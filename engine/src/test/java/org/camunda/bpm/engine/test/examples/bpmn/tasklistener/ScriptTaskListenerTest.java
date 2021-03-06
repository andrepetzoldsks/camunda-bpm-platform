/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.engine.test.examples.bpmn.tasklistener;

import org.camunda.bpm.engine.impl.test.PluggableProcessEngineTestCase;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;

/**
 * @author Rich Kroll
 */
public class ScriptTaskListenerTest extends PluggableProcessEngineTestCase {

	@Deployment(resources = { "org/camunda/bpm/engine/test/examples/bpmn/tasklistener/ScriptTaskListenerTest.bpmn20.xml" })
	public void testScriptTaskListener() {
		runtimeService.startProcessInstanceByKey("scriptTaskListenerProcess");
		Task task = taskService.createTaskQuery().singleResult();
		assertEquals("Name does not match", "All your base are belong to us", task.getName());
		
		taskService.complete(task.getId());

		Task task2 = taskService.createTaskQuery().singleResult();
		assertEquals("Task name not set with 'bar' variable", "BAR", task2.getName());
		
		Object bar = runtimeService.getVariable(task2.getExecutionId(), "bar");
		assertNull("Expected 'bar' variable to be local to script", bar);
		
		Object foo = runtimeService.getVariable(task2.getExecutionId(), "foo");
		assertEquals("Could not find the 'foo' variable in variable scope", "FOO", foo);
	}

}

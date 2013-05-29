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
package org.camunda.bpm.engine.impl;

import java.io.Serializable;
import java.util.List;

import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.impl.interceptor.CommandExecutor;
import org.camunda.bpm.engine.runtime.Incident;
import org.camunda.bpm.engine.runtime.IncidentQuery;

public class IncidentQueryImpl extends AbstractQuery<IncidentQuery, Incident> implements IncidentQuery, Serializable {

  private static final long serialVersionUID = 1L;
  
  protected String incidentType;
  protected String executionId;
  protected String activityId;
  protected String processInstanceId;
  protected String processDefinitionId;
  protected String causeId;
  protected String rootCauseId;
  
  public IncidentQueryImpl() {
  }

  public IncidentQueryImpl(CommandContext commandContext) {
    super(commandContext);
  }

  public IncidentQueryImpl(CommandExecutor commandExecutor) {
    super(commandExecutor);
  }

  public IncidentQuery processDefinitionId(String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
    return this;
  }

  public IncidentQuery processInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
    return this;
  }

  public IncidentQuery executionId(String executionId) {
    this.executionId = executionId;
    return this;
  }

  public IncidentQuery activityId(String activityId) {
    this.activityId = activityId;
    return this;
  }

  public IncidentQuery causeId(String causeId) {
    this.causeId = causeId;
    return this;
  }

  public IncidentQuery rootCauseId(String rootCauseId) {
    this.rootCauseId = rootCauseId;
    return this;
  }

  public IncidentQuery incidentType(String incidentType) {
    this.incidentType = incidentType;
    return this;
  }

  public long executeCount(CommandContext commandContext) {
    // TODO Auto-generated method stub
    return 0;
  }

  public List<Incident> executeList(CommandContext commandContext, Page page) {
    // TODO Auto-generated method stub
    return null;
  }
  
  public List<Incident> listPage(int firstResult, int maxResults) {
    // TODO Auto-generated method stub
    return null;
  }

  public IncidentQuery asc() {
    // TODO Auto-generated method stub
    return null;
  }

  public IncidentQuery desc() {
    // TODO Auto-generated method stub
    return null;
  }

  public long count() {
    // TODO Auto-generated method stub
    return 0;
  }

  public Incident singleResult() {
    // TODO Auto-generated method stub
    return null;
  }

  public List<Incident> list() {
    // TODO Auto-generated method stub
    return null;
  }

}
/*
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
*
*   http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/

package org.apache.samza.sql.util;

import java.util.stream.Collectors;
import org.apache.samza.sql.SamzaSqlRelRecord;
import org.apache.samza.sql.interfaces.SamzaRelTableKeyConverter;


/**
 * A sample {@link SamzaRelTableKeyConverter} used in tests to convert the join key to table format.
 */
public class SampleRelTableKeyConverter implements SamzaRelTableKeyConverter {

  @Override
  public Object convertToTableKeyFormat(SamzaSqlRelRecord relRecord) {
    if (relRecord.getFieldValues().get(0) instanceof SamzaSqlRelRecord) {
      relRecord = (SamzaSqlRelRecord) relRecord.getFieldValues().get(0);
    }
    return relRecord.getFieldValues()
        .stream()
        .map(x -> x == null ? null : x.toString())
        .collect(Collectors.toList())
        .get(0);
  }
}

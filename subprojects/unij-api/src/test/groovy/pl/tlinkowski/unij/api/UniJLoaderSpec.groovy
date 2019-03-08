/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2019 Tomasz Linkowski.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.tlinkowski.unij.api

import spock.lang.Specification

import pl.tlinkowski.unij.api.annotation.UniJService
import pl.tlinkowski.unij.exception.UniJException

/**
 * @author Tomasz Linkowski
 */
class UniJLoaderSpec extends Specification {

  //region SERVICE WITHOUT IMPLEMENTATION
  def "load() throws when service implementation is missing"() {
    when:
      UniJLoader.load(NoImplService)
    then:
      UniJException ex = thrown()
      ex.message.contains(NoImplService.name)
      ex.message.contains("service implementation not found")
  }

  interface NoImplService {
  }
  //endregion

  //region MISSING @UniJService ANNOTATION
  def "load() throws when @UniJService annotation is missing"() {
    when:
      UniJLoader.load(UnannotatedService)
    then:
      UniJException ex = thrown()
      ex.message.contains(UnannotatedServiceImpl.name)
      ex.message.contains("not annotated with")
  }

  interface UnannotatedService {
  }

  static class UnannotatedServiceImpl implements UnannotatedService {
  }
  //endregion

  //region DUPLICATE PRIORITY
  def "load() throws when two service implementations have the same priority"() {
    when:
      UniJLoader.load(DuplicatePriorityService)
    then:
      UniJException ex = thrown()
      ex.message.contains(DuplicatePriorityServiceImpl1.name)
      ex.message.contains(DuplicatePriorityServiceImpl2.name)
      ex.message.contains("same priority")
  }

  interface DuplicatePriorityService {
  }

  @UniJService(priority = 10)
  static class DuplicatePriorityServiceImpl1 implements DuplicatePriorityService {
  }

  @UniJService(priority = 10)
  static class DuplicatePriorityServiceImpl2 implements DuplicatePriorityService {
  }
  //endregion

  //region DIFFERENT PRIORITY
  def "load() selects implementation with a higher priority"() {
    when:
      def service = UniJLoader.load(DifferentPriorityService)
    then:
      service instanceof DifferentPriorityServiceImpl2
  }

  interface DifferentPriorityService {
  }

  @UniJService(priority = 10)
  static class DifferentPriorityServiceImpl1 implements DifferentPriorityService {
  }

  @UniJService(priority = -10)
  static class DifferentPriorityServiceImpl2 implements DifferentPriorityService {
  }
  //endregion
}

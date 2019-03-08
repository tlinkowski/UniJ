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
package pl.tlinkowski.unij.api;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import pl.tlinkowski.unij.api.annotation.UniJService;
import pl.tlinkowski.unij.exception.UniJException;

/**
 * Loads UniJ services (used internally by {@link UniJ}).
 *
 * @author Tomasz Linkowski
 */
@UtilityClass
@Slf4j
final class UniJLoader {

  static <S> S load(Class<S> serviceClass) {
    List<S> services = new ArrayList<>(4);
    ServiceLoader.load(serviceClass).forEach(services::add);
    validateLoadedServices(services, serviceClass);
    return selectService(services, serviceClass);
  }

  private static <S> void validateLoadedServices(Collection<S> services, Class<S> serviceClass) {
    if (services.isEmpty()) {
      throw new UniJException(String.format(
              "%s service implementation not found. Ensure proper unij-* module is on the classpath/modulepath",
              serviceClass.getName()
      ));
    }
    log.debug("{} service: found {}", serviceClass.getName(), services);
  }

  private static <S> S selectService(Collection<S> services, Class<S> serviceClass) {
    return selectService(buildPriorityServiceMap(services), serviceClass);
  }

  private static <S> S selectService(SortedMap<Integer, S> priorityServiceMap, Class<S> serviceClass) {
    Integer highestPriority = priorityServiceMap.firstKey();
    S highestPriorityService = priorityServiceMap.get(highestPriority);

    log.info(
            "{} service: selected {} (priority={})",
            serviceClass.getName(), className(highestPriorityService), highestPriority
    );
    return highestPriorityService;
  }

  private static <S> SortedMap<Integer, S> buildPriorityServiceMap(Collection<S> services) {
    return services.stream().collect(Collectors.toMap(
            UniJLoader::detectPriority, Function.identity(), UniJLoader::throwOnDuplicatePriority, TreeMap::new
    ));
  }

  private static int detectPriority(Object service) {
    UniJService uniJServiceAnn = service.getClass().getAnnotation(UniJService.class);
    if (uniJServiceAnn == null) {
      throw new UniJException(String.format(
              "Service implementation %s not annotated with @%s", className(service), UniJService.class.getSimpleName()
      ));
    }
    return uniJServiceAnn.priority();
  }

  private static <S> S throwOnDuplicatePriority(S service1, S service2) {
    throw new UniJException(String.format(
            "%s and %s have the same priority", className(service1), className(service2)
    ));
  }

  private static String className(Object object) {
    return object.getClass().getName();
  }
}

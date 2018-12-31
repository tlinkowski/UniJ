/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2018 Tomasz Linkowski.
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
package pl.tlinkowski.unij.core;

import java.util.*;

import lombok.Getter;
import lombok.experimental.UtilityClass;

import pl.tlinkowski.unij.core.provider.UnmodifiableListFactory;

/**
 * @author Tomasz Linkowski
 */
@UtilityClass
final class UniJ {

  @Getter(lazy = true)
  private static final UnmodifiableListFactory listFactory = load(UnmodifiableListFactory.class);

  //region LOAD
  private static <T> T load(Class<T> serviceClass) {
    List<T> services = new ArrayList<>();
    ServiceLoader.load(serviceClass).forEach(services::add);
    validateLoadedServices(services, serviceClass);
    return services.get(0); // TODO: https://github.com/tlinkowski/UniJ/issues/21
  }

  private static <T> void validateLoadedServices(Collection<T> services, Class<T> serviceClass) {
    if (services.isEmpty()) {
      throw new IllegalStateException(String.format(
              "No %s service found. Ensure proper unij-* module is on the classpath/modulepath", serviceClass
      ));
    }
  }
  //endregion
}

/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2018-2021 Tomasz Linkowski.
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
import pl.tlinkowski.unij.service.api.UniJService;

/**
 * UniJ service interfaces.
 * <p>
 * Note that all UniJ service implementations must be annotated with {@link UniJService} annotation.
 *
 * @author Tomasz Linkowski
 * @see java.util.ServiceLoader
 */
module pl.tlinkowski.unij.service.api {
  exports pl.tlinkowski.unij.service.api;
  exports pl.tlinkowski.unij.service.api.collect;
  exports pl.tlinkowski.unij.service.api.misc;

  requires static pl.tlinkowski.annotation.basic;
}

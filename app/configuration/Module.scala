package configuration

import com.google.inject.AbstractModule

/**
 * А это модуль, который должен активировать мой DataBaseInitializer, при run
 */
class Module extends AbstractModule{
  override def configure(): Unit = {
    bind(classOf[DataBaseInitializer]).asEagerSingleton()
  }
}

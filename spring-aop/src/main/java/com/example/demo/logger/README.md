
## Logger AOP
_[TestDome](https://www.testdome.com/questions/java-spring/logger-aop/65577)_

In the LoggerAOP class, using Spring AOP, intercept all calls to the public methods annotated with the LogExecution annotation, and call the log method on the logger field with the intercepted method's name as the data argument.

For example, calling the getNames method of the NameRepository class, which has LogExecution annotation, should print "getNames".


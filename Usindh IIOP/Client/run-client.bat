set path=C:\Program Files\Java\jdk1.8.0_291\bin
javac DynamicClient.java
java -classpath . -Djava.security.policy=sachal.policy -Djava.naming.factory.initial=com.sun.jndi.cosnaming.CNCtxFactory -Djava.naming.provider.url=iiop://localhost:1050  -Djava.rmi.server.codebase=http://localhost:80/usindhIIOP/  DynamicClient
pause


# multi-mod-archytype

1. prepare a maven project
4
1. execute   
5
`mvn -DdefaultEncoding=utf-8 -Darchetype.filteredExtensions=java archetype:create-from-project`
6
1. install   
7
`cd target/generated-sources/archetype/`   
8
`mvn install`
9
1. use it   
10
`cd /tmp/archetype/`   
11
`mvn archetype:generate -DarchetypeCatalog=local`
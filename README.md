# Simple Features Projection Java

#### Simple Features Projection Lib ####

The Simple Features Libraries were developed at the [National Geospatial-Intelligence Agency (NGA)](http://www.nga.mil/) in collaboration with [BIT Systems](https://www.caci.com/bit-systems/). The government has "unlimited rights" and is releasing this software to increase the impact of government investments by providing developers with the opportunity to take things in new directions. The software use, modification, and distribution rights are stipulated within the [MIT license](http://choosealicense.com/licenses/mit/).

### Pull Requests ###
If you'd like to contribute to this project, please make a pull request. We'll review the pull request and discuss the changes. All pull request contributions to this project will be released under the MIT license.

Software source code previously released under an open source license and then modified by NGA staff is considered a "joint work" (see 17 USC § 101); it is partially copyrighted, partially public domain, and as a whole is protected by the copyrights of the non-government authors and must be released according to the terms of the original open source license.

### About ###

[Simple Features Projection](http://ngageoint.github.io/simple-features-proj-java/) is a Java library for performing projection conversions between [Simple Feature](https://github.com/ngageoint/simple-features-java) Geometries.

### Usage ###

View the latest [Javadoc](http://ngageoint.github.io/simple-features-proj-java/docs/api/)

#### Transform ####

```java

//Geometry geometry = ...

Projection projection1 = ProjectionFactory.getProjection(
    ProjectionConstants.AUTHORITY_EPSG,
    ProjectionConstants.EPSG_WEB_MERCATOR);
Projection projection2 = ProjectionFactory.getProjection(
    ProjectionConstants.AUTHORITY_EPSG,
    ProjectionConstants.EPSG_WORLD_GEODETIC_SYSTEM);

GeometryTransform transform = GeometryTransform.create(projection1,
    projection2);

Geometry transformed = transform.transform(geometry);

```

### Installation ###

Pull from the [Maven Central Repository](http://search.maven.org/#artifactdetails|mil.nga.sf|sf-proj|4.3.2|jar) (JAR, POM, Source, Javadoc)

```xml

<dependency>
    <groupId>mil.nga.sf</groupId>
    <artifactId>sf-proj</artifactId>
    <version>4.3.2</version>
</dependency>

```

### Build ###

[![Build & Test](https://github.com/ngageoint/simple-features-proj-java/workflows/Build%20&%20Test/badge.svg)](https://github.com/ngageoint/simple-features-proj-java/actions/workflows/build-test.yml)

Build this repository using Eclipse and/or Maven:

    mvn clean install

### Remote Dependencies ###

* [Simple Features](https://github.com/ngageoint/simple-features-java) (The MIT License (MIT)) - Simple Features Lib
* [Projections](https://github.com/ngageoint/projections-java) (The MIT License (MIT)) - Projections Lib

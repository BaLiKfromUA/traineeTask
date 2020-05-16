# SANDBOX

## TIPS
### CUSTOM ANNOTATIONS
#### java.lang.annotation.ElementType:

A program element type. The constants of this enumerated type provide a simple classification of the declared elements in a Java program. These constants are used with the Target meta-annotation type to specify where it is legal to use an annotation type.

There are the following constants:

- **ANNOTATION_TYPE** - Annotation type declaration
- **CONSTRUCTOR** - Constructor declaration
- **FIELD** - Field declaration (includes enum constants)
- **LOCAL_VARIABLE** - Local variable declaration
- **METHOD** - Method declaration
- **PACKAGE** - Package declaration
- **PARAMETER** - Parameter declaration
- **TYPE** - Class, interface (including annotation type), or enum declaration

### SQL
`mysql -ubalik -p1204`

By default MySQL ignores differences in case and trailing spaces on varchar.

If you need it to be case sensitive, you can alter the table to be varchar(...) binary.

For case sensitive comparison use BINARY:
```$xslt
SELECT 'user' = 'UsEr' // true
SELECT BINARY 'user' = 'UsEr' // false
```


### Hibernate
####  property name="hbm2ddl.auto"
As per the documentation it can have four valid values:

create | update | validate | create-drop

Following is the explanation of the behaviour shown by these value:

- **create** :- create the schema, the data previously present (if there) in the schema is lost
- **update**:- update the schema with the given values.
- **validate**:- validate the schema. It makes no change in the DB.
- **create-drop**:- create the schema with destroying the data previously present(if there). It also drop the database schema when the SessionFactory is closed.

Following are the important points worth noting:

In case of update, if schema is not present in the DB then the schema is created.

In case of validate, if schema does not exists in DB, it is not created. Instead, it will throw an error:- Table not found:<table name>

In case of create-drop, schema is not dropped on closing the session. It drops only on closing the SessionFactory.

In case if i give any value to this property(say abc, instead of above four values discussed above) or it is just left blank. It shows following behaviour:

- If schema is not present in the DB:- It creates the schema

- If schema is present in the DB:- update the schema.


#### FetchType
- **FetchType.LAZY**: It fetches the child entities lazily, that is, at the time of fetching parent entity it just fetches proxy (created by cglib or any other utility) of the child entities and when you access any property of child entity then it is actually fetched by hibernate.

- **FetchType.EAGER**: it fetches the child entities along with parent.
#### GenerationType
- **AUTO**: Hibernate selects the generation strategy based on the used dialect,
- **IDENTITY**: Hibernate relies on an auto-incremented database column to generate the primary key,
- **SEQUENCE**: Hibernate requests the primary key value from a database sequence.
- **TABLE**: Hibernate uses a database table to simulate a sequence.


### Spring
For information about using other forms of metadata with the Spring container, see:

- Annotation-based configuration: Spring 2.5 introduced support for annotation-based configuration metadata.

- Java-based configuration: Starting with Spring 3.0, many features provided by the Spring JavaConfig project became part of the core Spring Framework.
 Thus you can define beans external to your application classes by using Java rather than XML files. To use these new features, see the @Configuration, @Bean, @Import and @DependsOn annotations.
 
#### @ControllerAdvice 
@ControllerAdvice is global: you can have a centralized way of handling exceptions, bindings, etc., it applies to the entire controller defined. 
 
### Redirect vs forward
```return "redirect:/books";```

It returns to the client (browser) which interprets the http response and automatically calls the redirect URL

```return "jsp/books/booksList";```

It process the JSP and send the HTML to the client

```return "forward:/books";```

It transfer the request and calls the URL direct in the server side.



To decide which one to use you have to consider some aspects of each approach:

- **Forward**: is faster, the client browser is not involved, the browser displays the original URL, the request is transfered do the forwarded URL.

- **Redirect**: is slower, the client browser is involved, the browser displays the redirected URL, it creates a new request to the redirected URL.
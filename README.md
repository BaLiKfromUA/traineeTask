# SANDBOX

## TIPS
### SQL
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

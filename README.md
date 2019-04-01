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
#### FetchType
- **FetchType.LAZY**: It fetches the child entities lazily, that is, at the time of fetching parent entity it just fetches proxy (created by cglib or any other utility) of the child entities and when you access any property of child entity then it is actually fetched by hibernate.

- **FetchType.EAGER**: it fetches the child entities along with parent.
#### GenerationType
- **AUTO**: Hibernate selects the generation strategy based on the used dialect,
- **IDENTITY**: Hibernate relies on an auto-incremented database column to generate the primary key,
- **SEQUENCE**: Hibernate requests the primary key value from a database sequence.
- **TABLE**: Hibernate uses a database table to simulate a sequence.

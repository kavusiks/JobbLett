module jobblett.json {
  requires transitive com.fasterxml.jackson.databind;
  requires com.fasterxml.jackson.datatype.jdk8;
  requires com.fasterxml.jackson.datatype.jsr310;
  requires transitive jobblett.core;

  exports jobblett.json;

  opens jobblett.json to com.fasterxml.jackson.databind;
}

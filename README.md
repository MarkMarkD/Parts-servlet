# TAL-servlet
Servlet outputs filtered list of entities in HTML format.

-There is a filter which allows to filter output entities.
-If none of the filter fields are specified then all entities are shown.
-The table can be sorted in ASC or DESC order by any of the columns by clicking on their header.
-Date format: MMM dd, yyyy.
-When user uses sorting - filtering is still applied

Patch #1: added "memory" to filter fields. Now they do not reset after filter is applied.

Patch #2: added sorting by column. 1 click - ASC order, next click - DESC. 
Fixed some bugs.

Patch #3: added interface.

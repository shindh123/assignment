rootProject.name = "assignment"

include("category-service")
include("category-core")
include("category-outbound")
include("category-inbound")
include("category-outbound:category-redis")
include("category-outbound:category-jpa")
include("category-inbound:category-service")

include("category-inbound:category-admin")
include("category-support")

include("category-common")

include("category-common:category-support")
include("category-common:category-test")

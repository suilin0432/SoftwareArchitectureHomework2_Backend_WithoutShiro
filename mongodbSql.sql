-- 权限添加操作
db.getCollection('permissions').insert([
    {
        "name" : "COMMODITY_INSERT",
        "resourceType" : "COMMODITY",
        "operationType" : "INSERT",
    },
    {
        "name" : "COMMODITY_DELETE",
        "resourceType" : "COMMODITY",
        "operationType" : "DELETE",
    },
    {    
        "name" : "COMMODITY_UPDATE",
        "resourceType" : "COMMODITY",
        "operationType" : "UPDATE",
    },
    {
        "name" : "COMMODITY_SEARCH",
        "resourceType" : "COMMODITY",
        "operationType" : "SEARCH",
    },
    {
        "name" : "CAR_INSERT",
        "resourceType" : "CAR",
        "operationType" : "INSERT",
    },
    {
        "name" : "CAR_DELETE",
        "resourceType" : "CAR",
        "operationType" : "DELETE",
    },
    {    
        "name" : "CAR_UPDATE",
        "resourceType" : "CAR",
        "operationType" : "UPDATE",
    },
    {
        "name" : "CAR_SEARCH",
        "resourceType" : "CAR",
        "operationType" : "SEARCH",
    },
    {
        "name" : "ROLE_DELETE",
        "resourceType" : "ROLE",
        "operationType" : "DELETE",
    },
    {    
        "name" : "ROLE_UPDATE",
        "resourceType" : "ROLE",
        "operationType" : "UPDATE",
    }
])

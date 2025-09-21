# Fault Dashboard - Dynamic Database Configuration

## Overview
This application connects to multiple Oracle databases with identical schema structures but different data. The system is configured to dynamically handle multiple database users through indexed configuration properties.

## Configuration

The application is currently configured for 5 Oracle users:
- ALBPBHRB
- BKPALGPBHRB
- BPBDMRAGTL
- BKPBPBDMRAGTL
- LMGBPBSCL

## How It Works

### Dynamic Configuration
The system uses indexed properties in `application.properties` to define multiple data sources:

```properties
# Number of configured data sources
app.datasource.count=5

# Data source configurations (indexed from 0 to count-1)
spring.datasource.user[0].url=jdbc:oracle:thin:@//192.168.89.11:1521/orcl
spring.datasource.user[0].driver-class-name=oracle.jdbc.OracleDriver
spring.datasource.user[0].username=ALBPBHRB
spring.datasource.user[0].password=ALBPBHRB
# ... and so on for other users
```

### Key Components

1. **DynamicDataSourceConfig**: Creates data sources, entity manager factories, and transaction managers dynamically based on the configuration.

2. **FaultRepository Interface**: Defines common repository methods used across all data sources.

3. **FaultRepositoryImpl**: Implements the repository interface with native SQL queries and proper EntityManager lifecycle management.

4. **DynamicRepositoryFactory**: Creates and manages repository instances for each data source, with proper autowiring.

5. **FaultService**: Uses the dynamic repositories to aggregate data from all configured data sources.

## Adding More Users

To add more database users:

1. Update the `app.datasource.count` property in `application.properties`
2. Add new indexed properties for each additional user
3. Restart the application

No code changes are required to support additional users.

## Maintenance

When making schema changes, ensure they are applied to all databases, as the application assumes identical schema structures across all data sources.

## Troubleshooting

If you encounter issues with the dynamic configuration:

1. Verify that the property names in `application.properties` match those used in the code
2. Check that the EntityManager is being properly created and closed
3. Ensure that all repositories are properly autowired
4. Verify that the database credentials and connection details are correct
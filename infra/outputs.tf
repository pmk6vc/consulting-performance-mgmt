output "db_connection_string" {
  value = module.cloud_sql.db_connection_name
  description = "Cloud SQL DB connection name"
}
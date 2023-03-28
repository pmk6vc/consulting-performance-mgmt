output "db_name" {
  value = google_sql_database_instance.db.name
  description = "Cloud SQL DB name"
}

output "db_region" {
  value = google_sql_database_instance.db.region
  description = "Cloud SQL DB region"
}
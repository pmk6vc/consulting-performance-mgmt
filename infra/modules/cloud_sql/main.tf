resource "random_id" "db_name_suffix" {
  byte_length = 4
}

#resource "google_sql_database_instance" "db" {
#  project = var.gcp_project_id
#  name = "postgres-${terraform.workspace}-${random_id.db_name_suffix.hex}"
#  database_version = "POSTGRES_14"
#  region = var.cloud_sql_region
#  deletion_protection = false
#
#  # TODO: Figure out how to limit access to app and local dev
#  # TODO: Add password validation policy
#  settings {
#    tier = "db-f1-micro"
#    activation_policy = "ALWAYS"
#    disk_size = 20
#    disk_autoresize = true
#    disk_autoresize_limit = 25
#    database_flags {
#      name  = "max_connections"
#      value = "50"
#    }
#  }
#}

#resource "google_sql_user" "db_user" {
#  instance = google_sql_database_instance.db.name
#  name = var.db_username
#  password = var.db_password
#}
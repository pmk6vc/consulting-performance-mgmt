data "google_secret_manager_secret_version" "db_username" {
  secret = var.db_username_secret
  version = var.db_username_secret_version
  sensitive = true
}

data "google_secret_manager_secret_version" "db_password" {
  secret = var.db_password_secret
  version = var.db_password_secret_version
  sensitive = true
}
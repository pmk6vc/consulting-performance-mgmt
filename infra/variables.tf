# Global common data
variable "gcp_project_id" {
  description = "GCP project ID"
  type        = string
}

# Secrets manager
variable "db_username_secret" {
  description = "Name of secret for DB username"
  type        = string
}

variable "db_username_secret_version" {
  description = "Version of secret for DB username"
  type        = number
}

variable "db_password_secret" {
  description = "Name of secret for DB password"
  type        = string
}

variable "db_password_secret_version" {
  description = "Version of secret for DB password"
  type        = number
}

# Cloud SQL config
variable "cloud_sql_region" {
  description = "Region for Cloud SQL DB instance"
  type        = string
}
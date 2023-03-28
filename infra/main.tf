terraform {
  required_providers {
    google = {
      source  = "hashicorp/google"
      version = "4.51.0"
    }
  }
  backend "gcs" {
    bucket = "zugzwang-terraform-backend"
    prefix = "terraform/state"
  }
}

provider "google" {
  project     = var.gcp_project_id
}

module "secrets" {
  source                     = "./modules/secrets"
  db_username_secret         = var.db_username_secret
  db_username_secret_version = var.db_username_secret_version
  db_password_secret         = var.db_password_secret
  db_password_secret_version = var.db_password_secret_version
}

module "cloud_sql" {
  source         = "./modules/cloud_sql"
  gcp_project_id = var.gcp_project_id
  cloud_sql_region = var.cloud_sql_region
  db_username    = module.secrets.db_username
  db_password    = module.secrets.db_password
}
# Be sure to restart your server when you modify this file.

# Your secret key for verifying cookie session data integrity.
# If you change this key, all old sessions will become invalid!
# Make sure the secret is at least 30 characters and all random, 
# no regular words or you'll be exposed to dictionary attacks.
ActionController::Base.session = {
  :key         => '_frontend_session',
  :secret      => 'da48ab7935a3b8b1e299d784069791e0cfbf89e7d5a5419edc66526ed11f8ed76483d21445a194b2e4db9a95f3bc043c8d7a524117617169567204d3f5d45b65'
}

# Use the database for sessions instead of the cookie-based default,
# which shouldn't be used to store highly confidential information
# (create the session table with "rake db:sessions:create")
# ActionController::Base.session_store = :active_record_store

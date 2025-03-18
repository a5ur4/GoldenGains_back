CREATE DATABASE IF NOT EXISTS 'goldengains';
USE 'goldengains';

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    weight DECIMAL(5,2),
    height DECIMAL(5,2),
    country VARCHAR(100)
);

CREATE TABLE lifts (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    gym VARCHAR(255),
    weight DECIMAL(6,2) NOT NULL,
    reps INT NOT NULL
);

CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    description TEXT
);

CREATE TABLE posts (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    user_id INT REFERENCES users(id) ON DELETE SET NULL,
    country VARCHAR(100),
    gym VARCHAR(255),
    text TEXT NOT NULL,
    images TEXT[], 
    posted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    upvote INT DEFAULT 0,
    downvote INT DEFAULT 0,
    category_id INT REFERENCES categories(id) ON DELETE SET NULL,
    badges TEXT[]
);

CREATE TABLE ranks (
    id SERIAL PRIMARY KEY,
    lift_id INT REFERENCES lifts(id) ON DELETE CASCADE,
    position INT NOT NULL
);

CREATE TABLE music (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    link TEXT NOT NULL,
    image TEXT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE news (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    category_id INT REFERENCES categories(id) ON DELETE SET NULL
);

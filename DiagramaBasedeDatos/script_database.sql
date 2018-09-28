--create tables

-- anime table
CREATE TABLE animes
(
  anime_id serial NOT NULL,
  anime_title character varying(300) NOT NULL,
  anime_description character varying NOT NULL,
  anime_one_start integer NOT NULL,
  anime_two_start integer NOT NULL,
  anime_three_start integer NOT NULL,
  anime_four_start integer NOT NULL,
  anime_five_start integer NOT NULL,
  anime_image bytea,
  anime_chapters text[][],
  anime_status character varying(100) NOT NULL,
  anime_release_date timestamp with time zone NOT NULL,
  PRIMARY KEY (anime_id)
);

-- series table

CREATE TABLE serie
(
  serie_id serial NOT NULL,
  serie_title character varying(300) NOT NULL,
  serie_description character varying NOT NULL,
  serie_one_start integer NOT NULL,
  serie_two_start integer NOT NULL,
  serie_three_start integer NOT NULL,
  serie_four_start integer NOT NULL,
  serie_five_start integer NOT NULL,
  serie_image bytea,
  serie_chapters text[][],
  serie_status character varying(100) NOT NULL,
  serie_release_date timestamp with time zone NOT NULL,
  PRIMARY KEY (serie_id)
);

-- users table

CREATE TABLE users
(
  user_id serial NOT NULL,
  user_name character varying(50) NOT NULL,
  user_password character varying(200) NOT NULL,
  user_email character varying(200) NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (user_id)
)

-- reviews table

CREATE TABLE reviews
(
  review_id serial NOT NULL,
  review_tittle character varying(200) NOT NULL,
  review_type character varying(50) NOT NULL,
  review_date timestamp with time zone NOT NULL,
  review_content character varying NOT NULL,
  review_image bytea,
  review_likes integer NOT NULL,
  review_dislikes integer NOT NULL,
  PRIMARY KEY (review_id)
);

-- categories table

CREATE TABLE categories
(
  category_id serial NOT NULL,
  category_name character varying(150) NOT NULL,
  PRIMARY KEY (category_id)
);

-- comments table

CREATE TABLE comments
(
  comment_id serial NOT NULL,
  comment_date timestamp with time zone NOT NULL,
  comment_user_id integer REFERENCES users(user_id) on DELETE set NULL on UPDATE CASCADE,
  comment_content text NOT NULL,
  comment_likes integer NOT NULL,
  comment_dislikes integer NOT NULL,
  PRIMARY KEY (comment_id)
);

-- relations table

-- animes relations

CREATE TABLE anime_category
(
  category_id integer REFERENCES categories(category_id) on DELETE set NULL on UPDATE CASCADE,
  anime_id integer REFERENCES animes(anime_id) on DELETE set NULL on UPDATE CASCADE,
  PRIMARY KEY (category_id, anime_id)
);

CREATE TABLE anime_review
(
  review_id integer REFERENCES reviews(review_id) on DELETE set null on UPDATE CASCADE,
  anime_id integer REFERENCES animes(anime_id) on DELETE set null on UPDATE CASCADE,
  PRIMARY KEY (review_id, anime_id)
);

-- series relations

CREATE TABLE serie_category
(
  category_id integer REFERENCES categories(category_id) on DELETE set null on UPDATE CASCADE,
  serie_id integer REFERENCES animes(anime_id)on DELETE set null on UPDATE CASCADE,
  PRIMARY KEY (category_id, serie_id)
);

CREATE TABLE serie_review
(
  review_id integer REFERENCES reviews(review_id) on DELETE set null on UPDATE CASCADE,
  serie_id integer REFERENCES serie(serie_id) on DELETE set null on UPDATE CASCADE,
  PRIMARY KEY (review_id, serie_id)
);

-- users relations

CREATE TABLE user_review
(
  user_id integer REFERENCES users(user_id) on DELETE set null on UPDATE CASCADE,
  review_id integer REFERENCES reviews(review_id) on DELETE set null on UPDATE CASCADE,
  PRIMARY KEY (user_id, review_id)
);

-- reviews relations

CREATE TABLE review_comment
(
  review_id integer REFERENCES reviews(review_id) on DELETE set null on UPDATE CASCADE,
  comment_id integer REFERENCES comments(comment_id) on DELETE set null on UPDATE CASCADE,
  comment_position integer NOT NULL,
  PRIMARY KEY (review_id, comment_id)
);



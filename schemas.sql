CREATE TABLE Users
(
    user_id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    email NVARCHAR(255) NOT NULL UNIQUE,
    password NVARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
        CHECK (role IN ('ADMIN', 'USER')),
    is_active BIT NOT NULL DEFAULT 1,
    created_date DATETIME2 NOT NULL DEFAULT GETDATE(),
    updated_date DATETIME2 NULL
);

CREATE TABLE Categories
(
    category_id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(100) NOT NULL UNIQUE,
    is_active BIT NOT NULL DEFAULT 1,
    created_date DATETIME2 NOT NULL DEFAULT GETDATE(),
    updated_date DATETIME2 NULL
);

CREATE TABLE Articles
(
    article_id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(250) NOT NULL,

    category_id INT NOT NULL,

    status VARCHAR(20) NOT NULL
        CHECK (status IN ('DRAFT', 'PUBLISHED')),

    published_date DATETIME2 NULL,

    created_by INT NOT NULL,

    created_date DATETIME2 NOT NULL DEFAULT GETDATE(),
    updated_date DATETIME2 NULL,

    CONSTRAINT FK_Articles_Category
        FOREIGN KEY (category_id)
        REFERENCES Categories(category_id),

    CONSTRAINT FK_Articles_User
        FOREIGN KEY (created_by)
        REFERENCES Users(user_id)
);
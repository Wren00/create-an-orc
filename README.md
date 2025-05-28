# Create An Orc project

This website provides a randomised name, stats, an description built from a prompt and a visual
picture of your character made from randomised pieces of pixel character art. This fantasy character will be an `orc` 
which will be communicated in the branding of the site.
The generation of a short description of the character will be done through prompts to OpenAI.
The randomised character will be regenerated on a page refresh. This is to quickly give a user 
seeking an idea for art or writing a compelling starting off point.

## Problem Definition
Creating a new character for a series or for a TTRPG can sometimes be a daunting process if people are
experiencing a creative block. 
This project intends to provide a compelling starting off point to encourage user ideas and creativity.
The design will focus on accessibility and speed to get creatives thinking with minimal effort, but will be
able to eventually include some more incentives for repeat users like saving and sharing their 
favourite random prompts.

## Who is it for?
This project is designed to provide a creative prompt for writers/artists/ttrpg players or anyone seeking a
prompt to form an idea for a fantasy character.

## MOSCOW analysis

### Must have
- A pixel art image of the randomised character. This has been created through:
  - A shadow base 
  - A face with skin colour 
  - Upper torso clothes 
  - Lower torso clothes 
- These individual images are layered together on the screen and will appear in the same place on page refresh
- A randomised name which has been built out of syllables provided in a JSON file.
- A randomised description created via prompts to Open AI. The prompts should have randomised values each time it is sent
through to the AI. The AI generates the description which will be sent back and displayed.
- Randomised Stats for the character
### Should have
- User accounts where a user can save instances of characters that they wish to keep record of. 
- Rate limiting to prevent an overload of AI requests. 
- The website should be viewable and usable on different screen sizes. 
### Could have
- Eventually expand to other kinds of fantasy characters, like elves, dwarves etc. Each new type of potential character
will need to be added as separate updates. 
- Users can share their favourite creations with other users. 
### Won't have
- A full gameified character sheet for ttrpg players as this would be too complex. 
- A massively expanded description of the character as this is just meant to be a simple prompt to inspire user
creativity. 

## Domain Model Diagram
```mermaid
erDiagram
USERS ||--|{ CHARACTERS : owns
USERS ||--|{ PROMPTS : creates
PROMPTS ||--|{ CHARACTERS : creates
```

### Dictionary
- **User**: Any person accessing the website and viewing a randomised character
- **Character**: The generated characters that are returned via the randomiser code and the AI. Initially this is just for Orcs as a race.
- **Prompt**: Randomised adjectives that will be provided in threes to the AI to generate a
character. For example an orc character may be:
  - Adventurous
  - Poor
  - Good
- **Catalogue**: Syllables stored within the databases used for the purpose of character name generation and suplementing prompts with Retrieval Augemented Generation (RAG).
- 

## Entity Relationship Diagram
```mermaid
erDiagram
direction TB
USERS {
	id int PK
	user_name string
	email_address string
	user_password string
	available_tokens int
	admin_privileges boolean 
	profile_id int
}
PROMPTS {
 	id int PK
	personality_prompt string
	appearance_prompt string
	mentality_prompt string
}
CHARACTERS {
 	id int PK
	name string
	prompt_id_1 int FK
	prompt_id_2 int FK
	prompt_id_3 int FK
	character_images_id int FK
}
CHARACTER_IMAGES {
	id int PK
	head int FK
	torso int FK
	legs int FK
}
CHARACTER_BODY_PART_IMAGES {
	id int
	url text 
	character_body_part_type_id int FK
}
CHARACTER_BODY_PART_IMAGE_TYPES {
	id int PK
	name string
}
BACKGROUND_IMAGES {
 	id int PK
	image_name string
	url string
}
CHARACTER_CATALOGUE {
	id int PK
	character_id int FK
	catalogue_id int FK
}
CATALOGUE {
    id int PK
    syllables string
}
	USERS ||--|{ PROMPTS : creates
	USERS ||--|{ CHARACTERS : owns
	CHARACTERS ||--|{ PROMPTS : uses
	CHARACTERS ||--|| CHARACTER_IMAGES : has
	CHARACTERS ||--|| CATALOGUE : uses
	CHARACTER_IMAGES ||--|{ CHARACTER_BODY_PART_IMAGES : uses
	CHARACTER_BODY_PART_IMAGES }|--|{ CHARACTER_BODY_PART_IMAGE_TYPES : uses
	CHARACTER_CATALOGUE ||--|{ CHARACTERS : generates
	
```


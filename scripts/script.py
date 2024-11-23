# For generating the rotated images from the original images

import os
from PIL import Image

# Directories
input_folder = "assets"
output_folder = "assets2"

# List of item names
items = [
    "BATHTUB", "CHAIR", "CIRCULAR_TABLE", "COUCH", "SIX_SEATER_DINING_TABLE",
    "DOUBLE_BED", "DOUBLE_BED_STAND", "DOUBLE_CUPBOARD", "DOUBLE_SOFA",
    "FOUR_SEATER_DINING_TABLE", "KITCHEN_COUNTER", "SINGLE_BED",
    "SINGLE_BED_STAND", "SINGLE_CUPBOARD", "SINGLE_SOFA", "SINK",
    "STOVE", "STUDY", "TOILET", "TRIPLE_CUPBOARD", "TRIPLE_SOFA", "TV",
    "WASH_BASIN"
]

# Ensure the output folder exists
os.makedirs(output_folder, exist_ok=True)

# Process each item
for item in items:
    item_name = item.lower()  # Convert to lowercase with underscores
    input_file = os.path.join(input_folder, f"{item_name}.png")
    
    # Skip if the file doesn't exist in the input folder
    if not os.path.exists(input_file):
        print(f"Warning: {input_file} does not exist.")
        continue
    
    # Create the output directory for the item
    item_output_dir = os.path.join(output_folder, item_name)
    os.makedirs(item_output_dir, exist_ok=True)
    
    # Load the original image
    try:
        with Image.open(input_file) as img:
            # Save original and rotated images
            for angle in [0, 90, 180, 270]:
                output_file = os.path.join(item_output_dir, f"DEGREES_{angle}.png")
                rotated_img = img.rotate(-angle, expand=True)  # Rotate clockwise
                rotated_img.save(output_file)
    except Exception as e:
        print(f"Error processing {input_file}: {e}")

print("Processing complete.")

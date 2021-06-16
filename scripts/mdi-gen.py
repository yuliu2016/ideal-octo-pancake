import sys
import os

with open("mdi-gen.txt") as f:
    src_path, target_path = f.readlines()

src_path = src_path.strip()
target_path = target_path.strip()

if len(sys.argv) > 1:
    mdi_name = sys.argv[1]
else:
    mdi_name = input("mdi-gen >> ")


src_f = os.path.join(src_path, mdi_name + ".svg")

with open(src_f) as f:
    mdi_data = f.read()

i = mdi_data.index("path d=\"") + 8

j = i
while mdi_data[j] != "\"":
    j += 1

mdi_path = mdi_data[i:j]

vec_name = mdi_name.replace("-", "_")

vec_content = f"""<!-- drawable/mdi_{vec_name}.xml -->
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="24dp"
    android:height="24dp"
    android:tint="@color/iconColor"
    android:viewportWidth="24"
    android:viewportHeight="24">
    <path
        android:fillColor="#000"
        android:pathData="{mdi_path}" />
</vector>"""

vec_path = os.path.join(target_path, f"mdi_{vec_name}.xml")
if os.path.exists(vec_path):
    print("Cannot overwrite existing file at", vec_path)
    sys.exit(1)

with open(vec_path, "w") as f:
    f.write(vec_content)
import { useEffect, useState } from "react";
import { InventoryTable } from "./components/InventoryTable";
import { ItemObject } from "./components/ItemObject";
import { ControlPanel } from "./components/ControlPanel";
import { formToItemObject } from "./components/formToItemObject";

function App() {
  const [inventory, setInventory] = useState<ItemObject[]>([]);
  const [loading, setLoading] = useState(false);

  const handleSubmitPost = (form: React.FormEvent<HTMLFormElement>) => {
    form.preventDefault();
    try {
      const newItem = formToItemObject(
        form.currentTarget.elements,
        inventory.length + 1
      );
      const newInventory = structuredClone(inventory);
      newInventory.push(newItem);
      setInventory(newInventory);
      fetch("http://localhost:8080/shop-inventory/addItem/", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newItem),
      });
    } catch (error) {
      console.error(error);
      alert("Invalid Input, please try again."); //The browser is so good at validating this hardly matters!
    }
  };

  const handleSubmitPut = (form: React.FormEvent<HTMLFormElement>) => {
    form.preventDefault();
    try {
      const index = parseInt(
        (form.currentTarget.elements.namedItem("itemID") as HTMLInputElement)
          .value
      );
      const newItem = formToItemObject(form.currentTarget.elements, index);
      const newInventory = structuredClone(inventory);
      newInventory[index - 1] = newItem;
      setInventory(newInventory);
      fetch("http://localhost:8080/shop-inventory/updateItem/" + index, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newItem),
      });
    } catch (error) {
      console.error(error);
      alert("Invalid Input, please try again."); //The browser is so good at validating this hardly matters!
    }
  };

  useEffect(() => {
    setLoading(true);

    const fetchData = async () => {
      const data = await fetch("http://localhost:8080/shop-inventory/");
      const json = await data.json();
      console.log(json);
      const itemArray: ItemObject[] = [];
      for (let i = 0; i < json.length; i++) {
        const currItem = new ItemObject(
          json[i].id,
          json[i].name,
          json[i].expiry,
          json[i].quantity,
          json[i].price
        );
        itemArray.push(currItem);
      }
      setInventory(itemArray);
    };
    fetchData().catch(console.error);
    setLoading(false);
  }, []);

  if (loading) {
    return <p>Loading...</p>;
  } else {
    return (
      <div>
        <header>
          <h1>Shop Inventory</h1>
        </header>
        <main className="contentWrapper">
          <InventoryTable itemArray={inventory}></InventoryTable>
          <ControlPanel
            handleSubmitPost={handleSubmitPost}
            handleSubmitPut={handleSubmitPut}
          ></ControlPanel>
        </main>
      </div>
    );
  }
}

export default App;

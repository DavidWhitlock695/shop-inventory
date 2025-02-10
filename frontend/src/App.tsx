import { useEffect, useState } from "react";
import { InventoryTable } from "./components/InventoryTable";
import { ItemObject } from "./components/ItemObject";
import { ControlPanel } from "./components/ControlPanel";
import { formToItemObject } from "./components/formToItemObject";

function App() {
  const [inventory, setInventory] = useState<ItemObject[]>([]);
  const [loading, setLoading] = useState(false);

  const fetchData = async () => {
    const data = await fetch("http://localhost:8080/shop-inventory/");
    const json = await data.json();
    const itemArray: ItemObject[] = [];
    for (let i = 0; i < json.length; i++) {
      const currItem = new ItemObject(
        json[i].id,
        json[i].name,
        json[i].expiry,
        json[i].quantity,
        json[i].price
      );
      console.log(currItem.itemName);
      itemArray.push(currItem);
    }
    const sortedArray = itemArray.sort((a, b) =>
      a.itemName.localeCompare(b.itemName)
    );
    setInventory(sortedArray);
  };

  const handleSubmitPost = (form: React.FormEvent<HTMLFormElement>) => {
    form.preventDefault();
    try {
      let newID: number = 0;
      inventory.forEach((elem) => {
        newID = Math.max(newID, elem.id);
      });
      newID += 1;
      const newItem = formToItemObject(form.currentTarget.elements, newID);
      const newInventory = structuredClone(inventory);
      newInventory.push(newItem);
      const sortedArray = newInventory.sort((a, b) =>
        a.itemName.localeCompare(b.itemName)
      );
      setInventory(sortedArray);
      fetch("http://localhost:8080/shop-inventory/addItem/", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newItem),
      }).then(() => fetchData());
    } catch (error) {
      console.error(error);
      alert("Error, please try again."); //The browser is so good at validating this hardly matters!
    }
    //Let's see if this works... to synchronise the data
    setLoading(true);
    fetchData().catch(console.error);
    setLoading(false);
  };

  const handleSubmitPut = (form: React.FormEvent<HTMLFormElement>) => {
    form.preventDefault();
    try {
      const index = parseInt(
        (form.currentTarget.elements.namedItem("row") as HTMLInputElement).value
      );
      //Check the row provided is valid
      if (index < 0 || index >= inventory.length) {
        throw new Error();
      }
      //The database updates based on a hash (id) which is not the row.
      const updatedUUID = inventory[index].id;
      const newItem = formToItemObject(
        form.currentTarget.elements,
        updatedUUID
      );
      const newInventory = structuredClone(inventory);
      newInventory[index] = newItem;
      const sortedArray = newInventory.sort((a, b) =>
        a.itemName.localeCompare(b.itemName)
      );
      setInventory(sortedArray);
      fetch("http://localhost:8080/shop-inventory/updateItem/" + updatedUUID, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newItem),
      }).then(() => fetchData());
    } catch (error) {
      console.error(error);
      alert("Error, please try again.");
    }
  };

  const handleSubmitDelete = (form: React.FormEvent<HTMLFormElement>) => {
    form.preventDefault();
    try {
      const index = parseInt(
        (form.currentTarget.elements.namedItem("row") as HTMLInputElement).value
      );
      //Check the row provided is valid
      if (index < 0 || index >= inventory.length) {
        throw new Error();
      }
      const newInventory = structuredClone(inventory);
      newInventory.splice(index, 1);
      const sortedArray = newInventory.sort((a, b) =>
        a.itemName.localeCompare(b.itemName)
      );
      setInventory(sortedArray);
      fetch("http://localhost:8080/shop-inventory/deleteItem/" + index, {
        method: "DELETE",
      }).then(() => fetchData());
    } catch (error) {
      console.error(error);
      alert("Error, please try again.");
    }
  };

  useEffect(() => {
    setLoading(true);
    const fetchInitialData = async () => {
      const data = await fetch("http://localhost:8080/shop-inventory/");
      const json = await data.json();
      const itemArray: ItemObject[] = [];
      for (let i = 0; i < json.length; i++) {
        const currItem = new ItemObject(
          json[i].id,
          json[i].name,
          json[i].expiry,
          json[i].quantity,
          json[i].price
        );
        console.log(currItem.itemName);
        itemArray.push(currItem);
      }
      const sortedArray = itemArray.sort((a, b) =>
        a.itemName.localeCompare(b.itemName)
      );
      setInventory(sortedArray);
    };
    fetchInitialData().catch(console.error);
    setLoading(false);
  }, []); //If you put the inventory here, it will perform endless GET/POST requests!

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
            handleSubmitDelete={handleSubmitDelete}
          ></ControlPanel>
        </main>
      </div>
    );
  }
}

export default App;

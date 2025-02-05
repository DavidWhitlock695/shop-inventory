import { useEffect, useState } from "react";
import { InventoryTable } from "./components/InventoryTable";
import { ItemObject } from "./components/ItemObject";
import { ControlPanel } from "./components/ControlPanel";

// const fetchData = async (): Promise<ItemObject[]> => {
//   const response = await fetch("http://localhost:8080/shop-inventory/");
//   try {
//     const jsonData = await response.json();
//     return jsonData;
//   } catch {}
//   if (!response.ok) {
//     throw new Error(response.statusText);
//   }
//   return [];
// };

function App() {
  const [inventory, setInventory] = useState<ItemObject[]>([]);
  const [loading, setLoading] = useState(false);

  const handleSubmit = (form: React.FormEvent<HTMLFormElement>) => {
    form.preventDefault();
    try {
      const name = (
        form.currentTarget.elements.namedItem("itemName") as HTMLInputElement
      ).value;
      const expiry = (
        form.currentTarget.elements.namedItem("itemExpiry") as HTMLInputElement
      ).value;
      const quantity = parseInt(
        (
          form.currentTarget.elements.namedItem(
            "itemQuantity"
          ) as HTMLInputElement
        ).value
      );
      const price =
        parseInt(
          (
            form.currentTarget.elements.namedItem(
              "itemPrice"
            ) as HTMLInputElement
          ).value
        ) * 100;
      console.log(name + " " + expiry + " " + quantity + " " + price);
      const newInventory = structuredClone(inventory);
      const newItem = new ItemObject(
        newInventory.length + 1,
        name,
        new Date(expiry).getTime(),
        quantity,
        price
      );
      newInventory.push(newItem);
      setInventory(newInventory);
      fetch("http://localhost:8080/shop-inventory/addItem/", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newItem),
      })
        .then((response) => response.json())
        .then((data) => console.log("User Created: ", data));
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
          <ControlPanel handleSubmit={handleSubmit}></ControlPanel>
        </main>
      </div>
    );
  }
}

export default App;

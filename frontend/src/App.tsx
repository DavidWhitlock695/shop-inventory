import { useEffect, useState } from "react";
import { InventoryTable } from "./components/InventoryTable";
import { ItemObject } from "./components/ItemObject";

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
    return <InventoryTable itemArray={inventory}></InventoryTable>;
  }
}

export default App;

import { useEffect, useState } from "react";
import { InventoryTable } from "./components/InventoryTable";

const [inventory, setInventory] = useState([]);
const [loading, setLoading] = useState(false);

useEffect(() => {
  setLoading(true);

  fetch("http://localhost:8080/shop-inventory/")
    .then((response) => response.json())
    .then((data) => {
      setInventory(data);
      setLoading(false);
    });
  console.log(inventory);
}, []);

function App() {
  if (loading) {
    return <p>Loading...</p>;
  } else {
    return <InventoryTable jsonData={""}></InventoryTable>;
  }
}

export default App;

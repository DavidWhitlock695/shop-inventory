export const ControlPanel = ({
  handleSubmitPost,
}: {
  handleSubmitPost: (form: React.FormEvent<HTMLFormElement>) => void;
}) => {
  return (
    <div className="controlPanel">
      <h2>Control Panel</h2>
      <h3>Create Entry</h3>
      <form
        action="POST"
        className="controlPanelSection"
        onSubmit={(form) => handleSubmitPost(form)}
      >
        <label className="controlPanelLabel" htmlFor="itemName">
          Name:
        </label>
        <input
          className="controlPanelInput"
          type="text"
          id="itemName"
          name="itemName"
          required={true}
          maxLength={20}
          size={15}
        />
        <label htmlFor="itemName" className="controlPanelLabel">
          Expiry:
        </label>
        <input
          className="controlPanelInput"
          type="date"
          id="itemExpiry"
          name="itemExpiry"
          required={true}
        />
        <label htmlFor="itemQuantity" className="controlPanelLabel">
          Quantity:
        </label>
        <input
          className="controlPanelInput"
          type="number"
          id="itemQuantity"
          name="itemQuantity"
          min={0}
          step={1}
          required={true}
        />
        <label htmlFor="itemPrice" className="controlPanelLabel">
          Price:
        </label>
        <input
          className="controlPanelInput"
          type="number"
          id="itemPrice"
          name="itemPrice"
          min={0}
          step={0.01}
          required={true}
        />
        <div className="submissionBox">
          <input type="submit"></input>
        </div>
      </form>
    </div>
  );
};

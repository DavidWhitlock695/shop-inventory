export const DeleteItemControl = ({
  handleSubmitDelete,
}: {
  handleSubmitDelete: (form: React.FormEvent<HTMLFormElement>) => void;
}) => {
  return (
    <div>
      <h3>Delete Entry</h3>
      <form
        action="DELETE"
        className="controlPanelSection"
        id="deleteSection"
        onSubmit={(form) => handleSubmitDelete(form)}
      >
        <label className="controlPanelLabel" htmlFor="itemID">
          ID:
        </label>
        <input
          className="controlPanelInput"
          type="number"
          id="itemID"
          name="itemID"
          min={1}
          step={1}
          required={true}
        />
        <div className="submissionBox">
          <input type="submit"></input>
        </div>
      </form>
    </div>
  );
};

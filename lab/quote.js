window.addEventListener("DOMContentLoaded", function () {
  document
    .querySelector("#fetchQuotesBtn")
    .addEventListener("click", function () {
      // Get values from drop-downs
      const topicDropdown = document.querySelector("#topicSelection");
      const selectedTopic =
        topicDropdown.options[topicDropdown.selectedIndex].value;
      const countDropdown = document.querySelector("#countSelection");
      const selectedCount =
        countDropdown.options[countDropdown.selectedIndex].value;

      // Get and display quotes
      fetchQuotes(selectedTopic, selectedCount);
    });
});

// TODO: Modify to use Fetch API
async function fetchQuotes(topic, count) {
  let url =
    "https://wp.zybooks.com/quotes.php?topic=" + topic + "&count=" + count;
  let response = await fetch(url);
  if (response.ok) {
    let json = await response.json();
    let html = "<ol>";
    for (let c = 1; c <= count; c++) {
      html += `<li>${JSON.stringify(json[c - 1].quote).replace(
        /\"/g,
        ""
      )} - ${JSON.stringify(json[c - 1].source).replace(/\"/g, "")}</li>`;
      // console.log(json[c]);
    }
    html += "</ol>";

    document.querySelector("#quotes").innerHTML = html;
    // console.log(html);
  } else {
    document.querySelector("#quotes").innerHTML =
      "Topic '" + topic + "' not found";
  }
}

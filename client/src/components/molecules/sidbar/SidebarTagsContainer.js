import styled from 'styled-components';
import Selected from '../../../styles/Selected';
import { SidebarTagsButton } from '../../atoms/Buttons';

const Container = styled.div`
  height: ${props => (props.isClicked ? `${props.tags.length * 30}px` : '0px')};
  overflow: hidden;
  transition: height 200ms ease-out;
`;

const SelectedButton = styled(SidebarTagsButton)`
  ${Selected}
`;

const SidebarTagsContainer = ({ isClicked, tags, onClick, selectedTab }) => {
  return (
    <Container isClicked={isClicked} tags={tags}>
      {tags.map(tag => {
        const [enName, krName] = tag;
        return selectedTab === krName ? (
          <SelectedButton to={`/posts/${enName}`} key={enName} onClick={onClick}>
            {krName}
          </SelectedButton>
        ) : (
          <SidebarTagsButton to={`/posts/${enName}`} key={enName} onClick={onClick}>
            {krName}
          </SidebarTagsButton>
        );
      })}
    </Container>
  );
};

export default SidebarTagsContainer;
